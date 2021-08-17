(ns mymain
  (:require [myio]))

(defn who_won_trick [trick]
  (eval (read-string (read-line)))
  )

(defn share_card_to_player [game players_cards]
  (assoc game
         :players
         (assoc
          (get game :players)
          (first players_cards)
          (assoc (get (get game :players) (first players_cards))
                 :cards
                 (second players_cards)))))

(defn set_round_count [game]
  (conj (range (get game :round_count)) game))

(defn shuffle_and_share_cards [game myshuffle]
   (reduce share_card_to_player game
           (map vector
                (keys (get game :players))
                (->>  (get game :cards)
                      (myshuffle)
                      (partition 2)))))



(defn play_card [game curr_player]
  (println curr_player)
  (println game)
  (let [played_card (myio/play_card_inp)]
    (assoc-in (assoc-in game
                        [:players curr_player :cards]
                        (remove #(= played_card %)
                                (get-in game [:players curr_player :cards])))

              [:current_trick]
              (conj (get-in game [:current_trick]) played_card))))


(defn play_round [game round]
    (reduce play_card (assoc-in game [:current_trick] '()) [:p1 :p2 :p3 :p4])
)

(defn play_rounds [game]
   (reduce play_round game (range (get game :round_count)))
)

(defn play_game []
  (->
   (myio/initialize_cards_and_players)
   (shuffle_and_share_cards myio/myshuffle)
   ;(announce)
   (play_rounds)
   )
)

(play_game)

;Try cloning this template project and look at how the files are laid out and how the ns form works: github.com/io-tupelo/clj-template – Alan Thompson 59 mins ago
;play_caerd
;
;play_rounds (play_round (play_card))
;
;
