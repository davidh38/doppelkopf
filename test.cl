(defn play_card_inp []
  (eval (read-string (read-line)))
 )

(defn who_won_trick [x]
  2
 )

(defn play_card [players_cards2, curr_player]
  (println curr_player)
  (println players_cards2)
  (println (second players_cards2))
  (let [played_card (play_card_inp)]
      [
           (assoc (first players_cards2) curr_player 
                (into []  (remove #(= played_card %) 
                    (get (first players_cards2) curr_player))
                )
             )
          (conj (second players_cards2) played_card)
      ]
  )
  )
(defn play_round [t myround]
 (let [round_players (first t)
      players2 (second t)
      mytricks []
 ]
 (println "in myround")
 (println t)
; [(take 4 (cycle (keys players))) 
 (concat [round_players]
 (reduce play_card (conj round_players [players2 mytricks]))
 )
;  []
; ]
)
 )

(def tricks [])
; init cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
;(def players {:p1 [], :p2 [], :p3[], :p4[]})
(def players {:p1 [], :p2 []}) ;, :p3 [], :p4 []})
; cards initialize
(def trick [])
; mix cards
(def mixed_cards (shuffle cards))
; share cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players (zipmap (keys players) (map vec (partition 4 mixed_cards))))
(def round_players (take 4 (drop (who_won_trick tricks) (cycle (keys players)))))

(reduce play_round 
         (conj 
          (range 10)
          [(take 2 (cycle (keys players))) players tricks]
)
         )

