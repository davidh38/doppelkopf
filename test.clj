(ns test
  (:require [test2 :refer :all])
)

(defn play_card_inp []
  (eval (read-string (read-line)))
  )


(defn who_won_trick [trick]
  (eval (read-string (read-line)) 
        )
  )

(defn share_card_to_player [game players_cards]
  (assoc game 
         :players 
         (assoc 
           (get game :players)
           (first players_cards)
           (assoc (get (get game :players) (first players_cards))
                  :cards   
                  (second players_cards) 
                  )
           )
         )       
  )

(defn shuffle_and_share_cards [game]
  (reduce share_card_to_player game
          (map vector (keys (get game :players))
                (partition 2 (shuffle (get game :cards))))
          )
  )

(defn initialize_cards_and_players []
  ; init cards
  (def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
  (def players '(:p1 :p2 :p3 :p4))

  ;(def round_players (take 4 (drop (who_won_trick tricks) (cycle (keys players)))))
  ; mix and share cards
  {:players (zipmap players (repeat  {:cards () :tricks ()}))
   :current_trick ()
   :round_start_player :p1
   :cards cards
   }
)

(defn shuffle_and_share_cards [game]
  (reduce share_card_to_player game
          (map vector (keys (get game :players))
                (partition 2 (shuffle (get game :cards))))
          )
  )


(defn play_card [game, curr_player]
  (println curr_player)
  (let [played_card (play_card_inp)]
    (assoc-in (assoc-in game
                        [:players curr_player :cards]
                        (remove #(= played_card %)
                                (get-in game [:players curr_player :cards])
                                )
                        )
               [:current_trick]
              (conj (get-in game [:current_trick]) played_card)
              )
    )
  )


(defn play_rounds [game round_count]
  (let [round_players [:p1 :p2 :p3 :p4]]
    (reduce play_card (assoc-in game [:current_trick] '()) round_players)
  )
)

(defn set_round_count [roundscount game]
       (conj (range roundscount) game)
)

(defn play_game []
  (->> (initialize_cards_and_players)
       (shuffle_and_share_cards)
       (set_round_count 2)
       (reduce play_round)
       )
)

;(play_game)
(showworld)
; change shuffle and share cards
