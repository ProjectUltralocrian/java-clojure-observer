(ns tommy.clojure.main
    (:import (com.tommy.java Window)))


(defn click-times [wnd times]
    (dotimes [_ times]
        (.clickOk wnd)))

(defn print-size [wnd]
    (let [width (get (.getSize wnd) 0)
          height (get (.getSize wnd) 1)
          name (clojure.string/upper-case (.getName wnd))
          msg (format "Window - name: %s, size: width %d, height %d" name width height)]
        (println msg)))

(defn exit-window [wnd]
    (.clickExit wnd))

(defn main-window-loop []

    (let [window (Window.)]

        (loop [turn 1]
            (println "TURN: " turn)
            (doto
                window
                (.setSize 666 333)
                (.setName "Tommy")
                (.clickOk)
                (.clickOk))

            (click-times window 5)
            (print-size window)
            (when (= turn 3)
                (exit-window window))

            (if (.isRunning window)
                (recur (inc turn))
                :finished-successfully))))


(defn -main []
    (main-window-loop))


