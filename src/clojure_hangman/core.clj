(ns clojure_hangman.core
  (:require [seesaw.core :as ss]
            [seesaw.bind :as ssb]))

(ss/native!)

(defn frame-component [content]
  (ss/frame
    :title "Test"
    :size [200 :by 200]
    :content content))

(defn run []
  (let [count (atom 0)
        route (atom 1)
        bg (ss/button-group)
        page-one-button (ss/button :group bg :text "Page One" :listen [:action (fn [_] (reset! route 1))])
        page-two-button (ss/button :group bg :text "Page Two" :listen [:action (fn [_] (reset! route 2))])
        page-one (ss/label :id :one :text "foo")
        page-two (ss/label :id :two :text "bar")
        nav (ss/horizontal-panel :items [page-one-button page-two-button])
        l (ss/label :text (str @count))
        inc (ss/button :text "Increase" :listen [:action (fn [_] (swap! count inc))])
        w (ss/flow-panel :align :center :items [page-one])
        content (ss/flow-panel :align :center :items [l inc w])
        container (ss/vertical-panel :items [nav content])
        frame (frame-component container)]

    (ssb/bind
      count
      (ssb/property l :text))

    (ssb/bind
      route
      (ssb/transform
        #(case %
           1 [page-one]
           2 [page-two]))
      (ssb/property w :items))

    (ss/show! frame)))

(defn -main
  [& _]
  (run))