(ns unittests
  (:require [mymain])
  (:require [clojure.test :as test])
  )

(test/deftest addition
  (test/is (= 4 (+ 2 2)) "addition")
  (test/is (= 4 (+ 3 1))))

(defn hey2 []
  (println "hello world")
  )


                                        ;        (test/is (= 2 3))
                                        ;        (test/is 8 (mymain/add 4 3))

                                        ;(print (mymain/myadd 4 3))


                                        ;(ns unittests
                                        ;  (:require [mymain])
                                        ;  (:require [clojure.test :as test])
                                        ;  )
                                        ;
                                        ;(test/deftest addition
                                        ;  (test/is (= 4 (+ 2 2)) "addition")
                                        ;  (test/is (= 4 (+ 3 1))))
                                        ;
                                        ;(defn hey2 []
                                        ;  (println "hello world")
                                        ;  )
                                        ;
                                        ;
                                        ;                                        ;        (test/is (= 2 3))
                                        ;                                        ;        (test/is 8 (mymain/add 4 3))
                                        ;
                                        ;(print (mymain/myadd 4 3))
