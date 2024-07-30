(ns tommy.clojure.core-test
    (:require [clojure.test :refer :all]
              [tommy.clojure.main :refer :all])
    (:import [com.tommy.java Window]))

(deftest create-window
    (testing "Can create a window"
        (let [window (new Window)]
            (doto window
                (.setName "Test"))
            (is (= (.getName window) "Test"))
            (is (= (get (.getSize window) 0) 600)))))
