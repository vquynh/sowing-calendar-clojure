(ns sowingcalendar.view
  (:require [hiccup.page :refer [html5 include-js include-css]]
            [hiccup.form :refer [form-to text-field submit-button]]
            [sowingcalendar.plants :refer [get-plants]]))


(defn get-style
  "Return the html style"
  []
    "html{
    color:white;
    text-align: center;
    height: 100%;
    background-image: url('https://cdn.pixabay.com/photo/2019/03/05/12/52/plant-4036131_960_720.jpg');
    height: 100%;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }"
)

(defn render-response
  "Given a map representing a requested month, return an HTML string for the list of vegetables to be seeded in this month."
  [month]
  (html5  [:head
          [:meta {:charset "UTF-8"}]]
          [:style (get-style)]
          [:body
            [:h1 [:center (get-plants month)]]          
          ])
  )

(defn render-form
  "Render a simple HTML form page."
  []
  (html5  [:head
          [:meta {:charset "UTF-8"}]]
          [:style (get-style)]
          [:body
            [:h1 "Bitte geben Sie einen Monat ein, um die auszusäenden Pflanzen zu sehen."]
            (form-to [:post "/"]
            (text-field { :cols 80
                          :rows 10} "content")
            [:div]
            (submit-button "Senden!"))
          ]))
