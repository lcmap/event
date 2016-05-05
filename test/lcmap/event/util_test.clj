(ns lcmap.event.util-test
  (:require [clojure.test :refer :all]
            [lcmap.event.util :as util]))

(deftest in?
  (is (util/in? [:a :b :c] :a))
  (is (not (util/in? [:a :b :c] :d))))

