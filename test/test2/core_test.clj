(ns test.test2.core-test
  (:require [clojure.test :refer :all]
            [test.test2.myfixture :as t :refer [myclosure]]
            [core :refer [play-game-reduce remove-card-from-player]]
            )
  )

(use-fixtures :each (t/myclosure))

(deftest play-game-reduce-test []
  (let [values (atom [[10 :h], [9 :d],[10 :h], [11 :d], [10 :h], [9 :s],[10 :s], [11 :s]])]
    (defn test-function []
      (let [e (first @values)]
        (swap! values rest)
        e)))

  (is (= (play-game-reduce test-function identity)
         {:players
          {:p1 {:cards ([10 :h]), :tricks ()},
           :p2 {:cards (), :tricks ()},
           :p3 {:cards (), :tricks ()},
           :p4 {:cards (), :tricks ()}},
          :current-trick
          [[:p1 [10 :s]] [:p2 [9 :s]] [:p3 [10 :s]] [:p4 [11 :s]]],
          :round-start-player :p1,
          :cards
          [[10 :h] [10 :h] [9 :d] [9 :s] [10 :s] [10 :h] [11 :d] [11 :s]],
          :round-count 2,
          :mode "normal"}
         )



      ))


(deftest remove-card-from-play-test []
  (is (= (core/remove-card-from-player  [14 :d] [[14 :d] [9 :d] [14 :d]])
         ([9 :d] [14 :d])))
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
