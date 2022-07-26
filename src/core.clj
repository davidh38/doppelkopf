(ns core
  (:require [io]))

(defn ja [] "ja")


(defn initialize-game [deck]
                                        ; init cards
  (let [players '(:p1 :p2 :p3 :p4)]

                                        ;(def round-players (take 4 (drop (who-won_trick tricks) (cycle (keys players)))))
                                        ; mix and share cards
    {:players (zipmap players (repeat  {:cards () :tricks ()}))
     :current-trick ()
     :round-start-player :p1
     :cards deck
     :round-count (/ (count deck) (count players))
     :mode ""
     }
    )
  )


(defn is-trump? [card]

  (contains? card  (into  (map vector (into (repeat 4 10) (repeat 4 11)) (cycle [:c :s :h :d]))
                          [[10 :h] [9 :d] [14 :d] [15 :d]])  )

  )



(defn who-won-trick [{current-trick :current-trick :as game} ]
                                        ;  (assoc game :players '())
                                        ;(assoc game :round-count 2)
                                        ; first served color
                                        ; anly the highest of the first person counts
                                        ; what is the highest trump


  game
  )


(defn assign-trick-to-players [game]
  game
  )

(defn share-card-to-player [game [player cards]]
  (assoc-in game [:players player :cards ]
            cards))

(defn share-cards-to-players [{players :players cards :cards :as game}]
  (reduce share-card-to-player game
          (map vector
               (keys players)
               (->>  cards
                     (partition (/ (count cards)
                                   (count players)))))))

(defn announce [game]

  game)

(defn play-card [io-play-card-inp game curr-player]
  (println curr-player)
  (clojure.pprint/pprint game)

  (let [played-card (io-play-card-inp)]
    (->
     (assoc-in game [:players curr-player :cards]
               (remove #(= played-card %) (get-in game [:players curr-player :cards])))

     (update :current-trick conj {curr-player played-card}))))

(defn create-deck []

  [[9 :s], [9 :c],[10 :c], [11 :c], [10 :s], [11 :s], [12 :s], [13 :s], [10 :s], [12 :s], [11 :s], [13 :s]]
                                        ;create full deck

                                        ; real deck
                                        ;(map vector (take 40 (cycle (range 9 16) )) (cycle [:h :d :s :d]))

  )

(defn trick-player-order [player]
  (take 4 (drop-while #(not= % player) [:p1 :p2 :p3 :p4 :p1 :p2 :p3 :p4]))
  )

(defn play-game-reduce [io-play-card-inp io-shuffle-deck]

  (->>
   (create-deck)
   (io-shuffle-deck)
   (initialize-game)
   (share-cards-to-players)
   (announce)
   ((fn play-game[game-init]
      (reduce
       (fn play-turn[game round-count]
         (->>
          (reduce
           (partial play-card io-play-card-inp) (assoc game :current-trick {}) (trick-player-order (game :round-start-player)))
          (who-won-trick)
          (assign-trick-to-players)
          )
         )
       game-init (range (game-init :round-count)))))
   )

  )


                                        ;(defn play-game []
                                        ;
                                        ;  (let [game-init
                                        ;        (->>
                                        ;         (io/initialize-cards-and-players)
                                        ;         (shuffle-and-share-cards io/myshuffle)
                                        ;         (announce))]
                                        ;
                                        ;    (loop [round 1 game game-init]
                                        ;
                                        ;      (let [game-next (loop [curr-player 1 game-next game]
                                        ;                        (if (> curr-player 4)
                                        ;                          game-next
                                        ;                          (recur (inc curr-player)
                                        ;                                 (play-card game-next (keyword (str "p" curr-player))))))]
                                        ;
                                        ;        (if (> round 10)
                                        ;          game-next
                                        ;          (recur (inc round) game-next))))))


                                        ;(play-game-reduce io/play-card-inp io/myshuffle)




(let [values (atom [[13 :s] [12 :s] [11 :s] [12 :s] [13 :s] [12 :s] [11 :s] [12 :s] [13 :s] [12 :s] [11 :s] [12 :s]])]
  (defn my-function2 []
    (let [e (first @values)]
      (swap! values rest)
      e)))

                                        ;(play-game-reduce my-function2 identity)
