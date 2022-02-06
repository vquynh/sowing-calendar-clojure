(ns sowingcalendar.plants)

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
