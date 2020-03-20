(defn play_card_inp []
  (eval (read-string (read-line)))
 )

(defn play_card [players_cards, curr_player]
  (println curr_player)
  (let [x (play_card_inp)]
  (assoc players_cards curr_player (remove #(= x %) (get players_cards curr_player))))
  )

(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
;(def players {:p1 [], :p2 [], :p3[], :p4[]})
(def players {:p1 [], :p2 []})
; cards initialize
(def trick [])
; mix cards
(def mixed_cards (shuffle cards))
; share cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players (zipmap (keys players) (partition 4 mixed_cards)))

(reduce play_card (conj (keys players) players))

(def player :p2)
(def played_card (play_card_inp))
(def players (assoc players player (remove #(= played_card %) (get players player))))
(def trick (conj trick played_card))



