(defn play_card_inp []
  (eval (read-string (read-line)))
  )

(defn who_won_trick [trick]
  (eval (read-string (read-line)) 
        )
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
    (println t)
    ; [(take 4 (cycle (keys players))) 
    (concat [round_players]
            (reduce play_card (conj round_players [players2 mytricks]))
            )
    ;  []
    ; ]
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
  (reduce share_card_to_player game (map vector (keys (get game :players))
                                    (partition 2 (shuffle (get game :cards)))
                                    )
  )
)

(defn initialize []
  ; init cards
  (def cards '([0 :c], [1 :c],[2 :c], [3 :c], [0 :s], [1 :s], [2 :s], [3 :s]))
  (def players '(:p1 :p2 :p3 :p4))

  ;(def round_players (take 4 (drop (who_won_trick tricks) (cycle (keys players)))))
  ; mix and share cards
    {:players (zipmap players (repeat  {:cards () :tricks ()}))
    :current_trick '()
    :round_start_player :p1
    :cards cards
    }
  )


(defn play_game []
  (shuffle_and_share_cards (initialize))
  (reduce play_round 
          (conj 
            (range 10)
            [(take 2 (cycle (keys players))) players []]
            )
          )
  )

(defn shuffle_and_share_cards [game]
  (reduce share_card_to_player game (map vector (keys (get game :players))
                                      (partition 2 (shuffle (get game :cards))))
))


