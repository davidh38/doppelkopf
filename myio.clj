(ns myio)

(defn play-card-inp []
  (eval (read-string (read-line))))

(defn myshuffle [cards]
  (shuffle cards)
  )

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
   :round-count (/ (count cards) (count players))})
