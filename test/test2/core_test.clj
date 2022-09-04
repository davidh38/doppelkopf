(ns test.test2.core-test
  (:require [clojure.test :refer :all]
            [test.test2.myfixture :as t :refer [myclosure]]
            )
  )

(use-fixtures :each (t/myclosure))

(deftest aoeu1 []
  (is (= "ja2" "ja2") )
  )

(deftest aoeu3 []
  (is (= "ja" "ja") )
  )


                                        ;(deftest play-one-trick []
                                        ;
                                        ;  (println (apply str (take 10 (repeat "#"))))
                                        ;  ;;,[10 :c], [11 :c], [10 :s], [11 :s], [12 :s], [13 :s], [10 :s], [12 :s], [11 :s], [13 :s]
                                        ;  (let [values (atom [[9 :s], [9 :c],[10 :s], [11 :s]])]
                                        ;    (defn test-function []
                                        ;      (let [e (first @values)]
                                        ;        (swap! values rest)
                                        ;        e)))
                                        ;
                                        ;
                                        ;  (play-game-reduce test-function identity)
                                        ;
                                        ;
                                        ;  )



                                        ;cider-run-project-test


                                        ;(test/is 8 (core/add 4 3))



                                        ;(print (mymain/myadd 4 3))
