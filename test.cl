
(def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
(def players {:p1 [], :p2 [], :p3[], :p4[]})
# cards initialize
 (def p1 {:handcards:[], :tricks [] })
# mix cards
(def mixed_cards (shuffle cards))
# share cards
(def players (zipmap (keys players) (partition 2 mixed_cards)))


(def play_card [plaer, players]
  (def played_card (read-line))
  (take played_card () )
 )


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

