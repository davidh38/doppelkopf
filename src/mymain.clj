(ns mymain
  (:require [myio :as io :refer :all]))

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

(defn shuffle-and-share-cards [myshuffle game]
  (reduce share-card-to-player game
          (map vector
               (keys (get game :players))
               (->>  (get game :cards)
                     (myshuffle)
                     (partition (/ (count (get game :cards))
                                   (count (get game :players))))))))

(defn announce [game]
  game)

(defn play-card [game curr-player]
  (println curr-player)
  (println game)

  (let [played-card (io/play-card-inp)]
    (->
     (assoc-in game [:players curr-player :cards]
               (remove #(= played-card %) (get-in game [:players curr-player :cards])))

     (assoc-in [:current-trick]
               (conj (game [:current-trick]) played-card)))))

(defn play-game-reduce []

  (let [game-init
        (->>
         (io/initialize-cards-and-players)
         (shuffle-and-share-cards myio/myshuffle)
         (announce))

        play-round
        (reduce play-card (assoc-in game-init [:current-trick] '()) [:p1 :p2 :p3 :p4])]

    (reduce play-round game-init (range (get game-init :round-count)))))

(defn play-game []

  (let [game-init
        (->>
         (io/initialize-cards-and-players)
         (shuffle-and-share-cards io/myshuffle)
         (announce))]

    (loop [round 1 game game-init]

      (let [game-next (loop [curr-player 1 game-next game]
                        (if (> curr-player 4)
                          game-next
                          (recur (inc curr-player)
                                 (play-card game-next (keyword (str "p" curr-player))))))]

        (if (> round 2)
          game-next
          (recur (inc round) game-next))))))

(play-game-reduce)
