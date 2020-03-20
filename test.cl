(defn play_card []
  (eval (read-string (read-line)))
 )

(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players {:p1 [], :p2 [], :p3[], :p4[]})
# cards initialize
(def trick [])
# mix cards
(def mixed_cards (shuffle cards))
# share cards
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players (zipmap (keys players) (partition 2 mixed_cards)))

(def played_card (play_card))
(def player :p1)
(def players (assoc players player (remove #(= played_card %) (get players player))))
(def trick (conj trick played_card))

(def played_card (play_card))
(def player :p2)
(def players (assoc players player (remove #(= played_card %) (get players player))))
(def trick (conj trick played_card))




# (def players (assoc players :p1 (first (partition 2 mixed_cards))))
# (def players (assoc players :p2 (second (partition 2 mixed_cards))))
# (def players (assoc players :p3 (third (partition 2 mixed_cards))))
# (def players (assoc players :p4 (fourth (partition 2 mixed_cards))))
# 
# (def mylist [first second third fourth])
# 
# (map share (keys players))
# 
# (defn share [player]
#    {player (first (partition 2 mixed_cards)}
# )
# 
# (share_on_players [cards, players]
#  (map share_on_player players)
#   )
# 
# (share_on_player [player]
#   conj(((take 1 cards) player))
#   )
# 
# play first round
# p1 plays card
# p2 plays card
# p3 plays card

determine_trick_for_player()

