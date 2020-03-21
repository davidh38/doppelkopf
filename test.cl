(defn play_card_inp []
  (eval (read-string (read-line)))
 )


(defn play_card [players_cards2, curr_player]
  (println curr_player)
  (println players_cards2)
  (println (second players_cards2))
  (let [x (play_card_inp)] 
      [
            (assoc (first players_cards2) curr_player 
              (into []  (remove #(= x %) 
                    (get (first players_cards2) curr_player))
              )
                 )
          (conj (second players_cards2) x)
      ]
  )
  )
; init cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
;(def players {:p1 [], :p2 [], :p3[], :p4[]})
(def players {:p1 [], :p2 []})
; cards initialize
(def trick [])
; mix cards
(def mixed_cards (shuffle cards))
; share cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players (zipmap (keys players) (map vec (partition 4 mixed_cards))))
(def tricks [])
(reduce play_card (conj (keys players) [players tricks]))

