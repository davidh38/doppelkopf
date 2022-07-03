(ns mymain
  (:require [myio :as io]))


(defn initialize-cards-and-players []
                                        ; init cards
  (def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
  (def players '(:p1 :p2 :p3 :p4))

                                        ;(def round-players (take 4 (drop (who-won_trick tricks) (cycle (keys players)))))
                                        ; mix and share cards
  {:players (zipmap players (repeat  {:cards () :tricks ()}))
   :current-trick ()
   :round-start-player :p1
   :cards cards
   :round-count (/ (count cards) (count players))
   :mode ""
   })

(defn who-won-trick [trick]
  (eval (read-string (read-line))))

(defn share-card-to-player [game players-cards]
  (assoc game
         :players
         (assoc
          (get game :players)
          (first players-cards)
          (assoc (get (game :players) (first players-cards))
                 :cards
                 (second players-cards)))))

(defn shuffle-and-share-cards [io-shuffle game]
  (reduce share-card-to-player game
          (map vector
               (keys (game :players))
               (->>  (game :cards)
                     (io-shuffle)
                     (partition (/ (count (game :cards))
                                   (count (game :players))))))))

(defn announce [game]
  game)

(defn play-card [io-play-card-inp game curr-player]
  (println curr-player)
  (println game)

  (let [played-card (io-play-card-inp)]
    (->
     (assoc-in game [:players curr-player :cards]
               (remove #(= played-card %) (get-in game [:players curr-player :cards])))

     (assoc-in [:current-trick]
               (conj (game [:current-trick]) played-card)))))

(defn play-game-reduce [io-play-card-inp io-shuffle]

  (let [game-init
        (->>
         (initialize-cards-and-players)
         (shuffle-and-share-cards io-shuffle)
         (announce))

        play-round
        (fn [game-init round-count] (reduce (partial play-card io-play-card-inp) (assoc-in game-init [:current-trick] '()) [:p1 :p2 :p3 :p4]))]

    (reduce play-round game-init (range (get game-init :round-count)))))

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
