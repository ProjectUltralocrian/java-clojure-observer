(ns tommy.clojure.main
    (:import (com.tommy.java Window)))


(defn click-times [wnd times]
    (dotimes [_ times]
        (.clickOk wnd)))

(defn print-size [wnd]
    (let [width (get (.getSize wnd) 0)
          height (get (.getSize wnd) 1)
          msg (format "Window - name: %s, size: width %d, height %d" (.getName wnd) width height)]
        (println msg)))

(defn exit-window [wnd]
    (.clickExit wnd))

(defn main-window-loop []

    (loop [window (Window.)]

        (doto
            window
            (.setSize 666 333)
            (.setName "Tommy")
            (.clickOk)
            (.clickOk))

        (click-times window 5)
        (print-size window)
        (exit-window window)

        ;;this does nothing, since the listeners are deregistered as part of the clickExit() method
        ;;of the Window class.
        (doto window
            (.deregisterListeners))

        (if (.isRunning window)
            (recur window)
            :finished-successfully)))


(defn -main []
    (main-window-loop))


