(ns clojure_hangman.core
  (:require [seesaw.core :as ss]
            [seesaw.bind :as ssb]))

(def state {:count (atom 1)})

(defn frame-component [label button]
  (ss/frame
    :title "Test"
    :size [200 :by 200]
    :content (ss/flow-panel
               :align :center
               :items [label button])))

(defn run [count]
  (let [l (ss/label :text (str @count))
        inc (ss/button :text "Increase" :listen [:action (fn [_] (swap! count inc))])
        frame (frame-component l inc)]
    (ssb/bind
      count
      (ssb/property l :text))
    (ss/show! frame))
  ;(ss/show! (window state))
  )

(defn -main
  [& _]
  (run (:count state)))