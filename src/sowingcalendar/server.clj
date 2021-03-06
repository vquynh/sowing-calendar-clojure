(ns sowingcalendar.server
  (:require [com.stuartsierra.component :as component]
            [bidi.ring :refer [make-handler]]
            [aleph.http :as http]
            [ring.util.response :as res]
            [ring.util.request :as req]
            [ring.middleware.params :refer [wrap-params]]
            [sowingcalendar.view :as view]
            [sowingcalendar.store :as store]))

(defn handle-post
  "This handles creating a new request, based on the POST data."
  [store request]
  (let [content (get (:form-params request) "content")
        uuid (store/add-new-request store content)]
    (res/redirect (str "/" uuid) :see-other)))

(defn handle-index
  "On index page, a form is rendered with the text field to enter a month and a submit button to send the request."
  [request]
  (res/response (view/render-form)))

(defn index-handler
  "Handle requests sent to root URL.

  They can be either GET requests (the user is looking at the form), or POST
  requests (the user just POSTed a new request)."
  [store request]
  (if (= (:request-method request) :post)
    (handle-post store request)
    (handle-index request)))

(defn request-handler
  [store request]
  (let [request (store/get-request-by-uuid store (:uuid (:route-params request)))]
    (res/response (view/render-response (:content request)))))

(defn handler
  "Get the handler function for the routes: root path for index and /uuid path for showing response for given request"
  [store]
  (make-handler ["/" {"" (partial index-handler store)
                      [:uuid] (partial request-handler store)}]))

(defn app
  [store]
  (-> (handler store)
      wrap-params))

(defrecord HttpServer [server]

  component/Lifecycle

  (start [this]
    (assoc this :server (http/start-server (app (:store this)) {:port 8080})))

  (stop [this]
    (dissoc this :server)))

(defn make-server
  []
  (map->HttpServer {}))
