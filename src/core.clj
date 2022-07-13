(ns core
  (:require [io]))

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

(defn who-won-trick [trick]
  (eval (read-string (read-line))))

(defn share-card-to-player [game [player cards]]
  (assoc-in game [:players player :cards ]
            cards))

(defn shuffle-and-share-cards [io-shuffle {players :players cards :cards :as game}]
  (reduce share-card-to-player game
          (map vector
               (keys players)
               (->>  cards
                     (io-shuffle)
                     (partition (/ (count cards)
                                   (count players)))))))

(defn announce [game]
  game)

(defn play-card [io-play-card-inp game curr-player]
  (println curr-player)
  (println game)

  (let [played-card (io-play-card-inp)]
    (->
     (assoc-in game [:players curr-player :cards]
               (remove #(= played-card %) (get-in game [:players curr-player :cards])))

     (update :current-trick conj played-card))))

(defn new-deck []

  '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s])

  )


(defn play-game-reduce [io-play-card-inp io-shuffle]

  (let [game-init
        (->>
         (new-deck)
         (initialize-game)
                                        ; (initialize players)
         (shuffle-and-share-cards io-shuffle)
         (announce))

        turns
        (fn [game-init round-count]
          (reduce (partial play-card io-play-card-inp) (assoc game-init :current-trick '()) [:p1 :p2 :p3 :p4]))]

    (reduce turns game-init (range (game-init :round-count)))))




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

(play-game-reduce io/play-card-inp io/myshuffle)
