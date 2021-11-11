(ns mymain
  (:require [myio]))

(defn who-won-trick [trick]
  (eval (read-string (read-line)))
  )

(defn share-card-to-player [game players-cards]
  (assoc game
         :players
         (assoc
          (get game :players)
          (first players-cards)
          (assoc (get (game :players) (first players-cards))
                 :cards
                 (second players-cards)))))

(defn shuffle-and-share-cards [game myshuffle]
   (reduce share-card-to-player game
           (map vector
                (keys (get game :players))
                (->>  (get game :cards)
                      (myshuffle)
                      (partition (/ (count (get game :cards))
                                    (count (get game :players))))))
           ))



(defn play-card [game curr-player]
  (println curr-player)
  (println game)
  (let [played-card (myio/play-card-inp)]
    (->
        (assoc-in game [:players curr-player :cards]
                (remove #(= played-card %) (get-in game [:players curr-player :cards])))

        (assoc-in [:current-trick]
                (conj (game [:current-trick]) played-card))))
    )

(defn play-round [game round]
    (reduce play-card (assoc-in game [:current-trick] '()) [:p1 :p2 :p3 :p4])
)

(defn play-rounds [game]
   (reduce play-round game (range (get game :round-count)))
)

(defn play-game []
  (->
   (myio/initialize-cards-and-players)
   (shuffle-and-share-cards myio/myshuffle)
   ;(announce)
   (play-rounds)
  )
)

(play-game)


;Try cloning this template project and look at how the files are laid out and how the ns form works: github.com/io-tupelo/clj-template – Alan Thompson 59 mins ago
;play-caerd
;play-rounds (play-round (play-card))
