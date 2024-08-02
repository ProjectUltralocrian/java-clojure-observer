(ns tommy.clojure.core-test
    (:require [clojure.test :refer :all]
              [tommy.clojure.main :refer :all]
              [clojure.spec.alpha :as spec]
              [clojure.spec.test.alpha :as stest]
              [clojure.string :refer [blank? upper-case]]
              [clojure.test.check.generators :as gen])
    (:import [com.tommy.java Window]))

(deftest create-window
    (testing "Can create a window"
        (let [window (new Window)
              [width height] (.getSize window)]
            (doto window
                (.setName "Test"))
            (is (= (.getName window) "Test"))
            (is (= [width height] [600 800])))))


(spec/def ::name
    (spec/with-gen
        (spec/and string? (comp not blank?))
        (fn []
            (gen/such-that
                (fn [x] (> 444 (count x)))
                gen/string))))

(spec/fdef hello
           :args (spec/cat :name ::name)
           :ret ::name
           :fn (fn [{:keys [args ret]}]
                   (= (upper-case (:name args)) ret)))
;:fn #(= (:name (:args %)) (upper-case (:ret %))))

(defn hello [name]
    (upper-case name))

(defn get-num [] (or nil :error))

(stest/instrument 'tommy.clojure.proptesting/hello)
(deftest prop-test
    (testing "Hello function returns upper-case version of argument."
     (let [result (stest/abbrev-result (first (stest/check `hello)))]
        (is (= (:failure result) nil)))))

(spec/exercise ::name)