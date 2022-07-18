(ns core-test
  (:require [clojure.test :as test]
            [core :refer :all]
            [hi :refer :all]
            )
  )

(test/deftest addition
  (test/is (= "ja" (core/ja)))
  (test/is (= 5 (+ 2 3)) "addition")
  (test/is (= 4 (+ 3 1))))

(test/deftest aoeuaddition2
  (test/is (= 6 (+ 2 3)) "addition")
  (test/is (= 4 (+ 3 1))))

                                        ;cider-run-project-test


                                        ;(test/is 8 (core/add 4 3))



                                        ;(print (mymain/myadd 4 3))
