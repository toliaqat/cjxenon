(ns cjxenon.core-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :refer :all]
            [clojure.pprint :refer [pprint]]
            [cjxenon.core :as x]))

(def c (x/connect "http://127.0.0.1:8000"))

; Delete all data before each test
(use-fixtures :each #(do (x/delete-all! c nil) (%)))

(deftest cas-test!
  (let [k (str (java.util.UUID/randomUUID))]
  (x/reset! c k "hi")
  (is (false? (x/cas! c k 10 "next")))
  (is (= "hi" (x/get c k)))

  (x/cas! c k 0 "hello")
  (is (= "hello" (x/get c k)))))


(deftest list-directory
  (is (= (x/get c nil)
         nil))

  (x/reset! c "foo" 1)
  (x/reset! c "bar" 2)

  (is (= (x/get-all-keys c)
         ["foo" "bar"])))

(deftest reset-get-test
  (testing "a simple key"
    (x/create! c "foo" "value")
    (x/reset! c "foo" "hi")
    (is (= "hi" (x/get c "foo")))))

(deftest create-test!
  (let [r (str (java.util.UUID/randomUUID))
        kk (str (rand-int 500))
        k (x/create! c kk r)]
    (is (= kk k))
    (is (= r (x/get c k)))))

;(deftest getv-test
;  (x/reset! c "foo" "hi")
;  (is (= ["hi" 0] (x/getv c "foo"))))
