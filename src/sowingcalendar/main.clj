(ns sowingcalendar.main
  (:require [sowingcalendar.system :refer [init-system start!]]))

(defn -main [& args]
  (init-system)
  (start!))
