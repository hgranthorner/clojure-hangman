(ns clojure_hangman.core
  (:require [seesaw.core :as ss]))

(defn foo []
  (println "Hello the world"))

(def state {:count (atom 1)})

(defn items [state]
  [(ss/text (str @(:count state)))
   (ss/button
     :text "Click Me!"
     :listen [:action #(ss/repaint! (ss/select (ss/to-root %) [:*]))])
   (ss/button
     :text "Inc"
     :listen [:action (fn [_]
                        (swap! (:count state) inc)
                        (println @(:count state)))])])

(defn window [state]
  (ss/frame
    :title "First Example"
    :content (ss/flow-panel
               :align :center
               :items (items state))
    :width 200
    :height 200))

(defn run []
  (ss/show! (window state)))

(defn -main
  [& _]
  (run))