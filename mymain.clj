(ns mymain
  (:require [myio]))

(defn who_won_trick [trick]
  (eval (read-string (read-line))))

(defn share_card_to_player [game players_cards]
  (assoc game
         :players
         (assoc
          (get game :players)
          (first players_cards)
          (assoc (get (get game :players) (first players_cards))
                 :cards
                 (second players_cards)))))

(defn shuffle_and_share_cards [game]
  (->>
   (reduce share_card_to_player game
           (map vector
                (keys (get game :players))
                (->>  (get game :cards)
                      (myio/myshuffle)
                      (partition 2))
            )
    )
   (set_round_count)))

(defn play_card [game, curr_player]
  (println curr_player)
  (println game)
  (let [played_card (myio/play_card_inp)]
    (assoc-in (assoc-in game
                        [:players curr_player :cards]
                        (remove #(= played_card %)
                                (get-in game [:players curr_player :cards])))

              [:current_trick]
              (conj (get-in game [:current_trick]) played_card))))

(defn play_rounds [game round_count]
  (let [round_players [:p1 :p2 :p3 :p4]]
        (reduce play_card
                (assoc-in game [:current_trick] '()) round_players)

   )

)

(defn set_round_count [game]
  (conj (range (get game :round_count)) game))

(defn play_game [game]
  (->> (shuffle_and_share_cards game)
       (reduce play_rounds)))

(defn play_game []
  (->>
   (myio/initialize_cards_and_players)
   (shuffle_and_share_cards)
   (reduce play_rounds)))

(play_game)


;Try cloning this template project and look at how the files are laid out and how the ns form works: github.com/io-tupelo/clj-template – Alan Thompson 59 mins ago
