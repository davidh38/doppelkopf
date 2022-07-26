(ns core-test
  (:require [clojure.test :refer :all]
            [core :refer :all]
            )
  )

(deftest aoeu1 []
  (is (= (ja) "ja") )
  )

(deftest aoeu2 []
  (is (= (ja) "ja2") )
  )

(deftest aoeu3 []
  (is (= (ja) "ja") )
  )


                                        ;cider-run-project-test


                                        ;(test/is 8 (core/add 4 3))



                                        ;(print (mymain/myadd 4 3))
