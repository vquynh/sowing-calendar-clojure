(ns sowingcalendar.view
  (:require [hiccup.page :refer [html5 include-js include-css]]
            [hiccup.form :refer [form-to text-field submit-button]]))


(def plants {
    "Januar" "Paprika, Blattsalat, Chilli",
    "Februar" "Kopfsalat, Aubergine, Tomaten, Paprika, Blattsalat, Chilli, Brokkoli, Fenchel, Gurke, Porree/Lauch, Pastinake"
    "März" "Kopfsalat, Aubergine, Tomaten, Kürbisse, Rosenkohl, Paprika, Blattsalat, Chilli, Zucchini, Brokkoli, Fenchel, Kohlrabi, Gurke, Porree/Lauch, Pastinake",
    "April" "Kopfsalat, Aubergine, Tomaten, Kürbisse, Rosenkohl, Paprika, Blattsalat, Chilli, Zucchini, Brokkoli, Fenchel, Zuckermais, Kohlrabi, Gurke, Porree/Lauch",
    "Mai" "Kopfsalat, Tomaten, Kürbisse, Rosenkohl, Blattsalat, Zucchini, Brokkoli, Grünkohl, Zuckermais, Kohlrabi",
    "Juni" "Grünkohl, Zuckermais, Kohlrabi",
    "Juli" "Kohlrabi",
    "August" "Blattsalat",
    "September" "Blattsalat",
    "Oktober" "Blattsalat",
    "November" "Keine",
    "Dezember" "Keine" })


(defn get-plants
  "Given a month, return a list of plant(s) to be seeded in this month."
  [month]
  (if (nil? (get plants month)) (format "%s ist kein gültiger Monat" month) (format "Diese Pflanzen kann man im %s aussäen: <br> %s" month (get plants month))
  )
)

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
