

(ns core
  (:require [io] [clojure.string :as str])
  (:import  java.util.Date))


(defn remove-card-from-player [card players-cards]

  (let [card-split (split-with #(not= card %) players-cards)]
    (concat (first  card-split) (rest (second card-split)))
    )
  )


(defn initialize-game
  "Game initialization"
  [deck]
  (let [players '(:p1 :p2 :p3 :p4)]

    {:players (zipmap players (repeat  {:cards () :tricks ()}))
     :current-trick ()
     :round-start-player :p1
     :cards deck
     :round-count (/ (count deck) (count players))
     :mode "normal"
     :re-team nil
     }))

(defn is-trump? [card]

  (some  #(= card %)
         (into  [[10 :h] [9 :d] [14 :d] [15 :d]]
                (map vector (into (repeat 4 10) (repeat 4 11)) (cycle [:c :s :h :d])))))

(defn determine-re-team [game]
  game


  )

(defn collect-points [cards]
  (->>
   (for [[v x y z] cards] [(second v) (second x) (second y) (second z)])
   (reduce (fn [c d ] (concat c d) ))

   )

  )


(defn card-value [serve-color card]
  (case card
    [10 :h] 20
    [13 :c] 19
    [13 :s] 18
    [13 :h] 17
    [13 :d] 16
    [12 :c] 15
    [12 :s] 14
    [12 :h] 12
    [12 :d] 11
    [10 :d] 10
    [9 :d] 9
    [11 serve-color] 8
    [10 serve-color] 7
    [14 serve-color] 6
    [9 serve-color] 5
    0))

(defn determine-trick-winner [cards]

  (->>
   (apply max-key (partial card-value (second (second (first cards))))
          (map second cards))
   (.indexOf (map second cards))
   (get cards)
   (first)))

(defn assign-trick-to-winner [{current-trick :current-trick mode :mode :as game}]
                                        ;(let [[n m] (split-with (partial not= :b) [:a :b :c :b :d])] (concat n (rest m)))
  (if  (= mode "normal")
    (do
      (if (some? is-trump?)
        (assoc
         (update-in game [:players (determine-trick-winner current-trick) :tricks]  conj current-trick)
         :round-start-player
         (determine-trick-winner current-trick))

        )
                                        ;if no trump, highest non trump wins
                                        ;highest first trump wins
      )
    game))

(defn print-game [game]
  (clojure.pprint/pprint game)
  game
  )

(defn share-card-to-player [game [player cards]]
  (assoc-in game [:players player :cards]
            cards))

(defn share-cards-to-players [{players :players cards :cards :as game}]
  (reduce share-card-to-player game
          (map vector
               (keys players)
               (->>  cards
                     (partition (/ (count cards)
                                   (count players)))))))

(defn announce-game-type [game]

  game)

(defn play-card [io-play-card-inp game curr-player]

  (let [played-card (io-play-card-inp)]
    (->
     (assoc-in game [:players curr-player :cards]
               (remove-card-from-player played-card (get-in game [:players curr-player :cards])))

     (update :current-trick conj [curr-player played-card]))))

(defn create-deck
  "Create a deck with 40 cards
  9 - 9
  10 - 10
  11 - As
  12 - jack
  13 - queen
  14 - king
  "
  []
  [[10 :h],[10 :h] [9 :d],[9 :s] [10 :s],[10 :h], [11 :d] [11 :s]]
                                        ; real deck
                                        ;(map vector (take 40 (cycle (range 9 16) )) (cycle [:h :d :s :d]))
  )

(defn trick-player-order [player]
  (take 4 (drop-while #(not= % player) [:p1 :p2 :p3 :p4 :p1 :p2 :p3 :p4])))

(defn play-game-reduce
  "play a full game"
  [io-play-card-inp io-shuffle-deck]
  (println "-------------- Start game ----------------")
  (->>
   (create-deck)
   (io-shuffle-deck)
   (initialize-game)
   (share-cards-to-players)
                                        ;   (announce-game-type)
                                        ;(determine-re-team)

   ;)
  ((fn play-game [game-init]
     (reduce
      (fn play-turn [game _]
        (->>
         (reduce
          (partial play-card io-play-card-inp) (assoc game :current-trick []) (trick-player-order (game :round-start-player)))
         (assign-trick-to-winner)
         (print-game)
         ))
      game-init (range (game-init :round-count)))))

  )
)


(let [values (atom [[10 :h], [9 :d],[10 :h], [11 :d], [10 :h], [9 :s],[10 :s], [11 :s]])]
(defn test-function []
  (let [e (first @values)]
    (swap! values rest)
    e)))

(play-game-reduce test-function identity)



                                        ;(defn mytest []
                                        ;  (let [a
                                        ;        '([[:p1 [10 :h]] [:p2 [9 :s]] [:p3 [10 :s]] [:p4 [11 :s]]]
                                        ;          [[:p1 [10 :h]] [:p2 [9 :d]] [:p3 [10 :h]] [:p4 [11 :d]]])]
                                        ;    a
                                        ;    )
                                        ;  )

(def a '([[:p1 [10 :h]] [:p2 [9 :s]] [:p3 [10 :s]] [:p4 [11 :s]]]
         [[:p1 [10 :h]] [:p2 [9 :d]] [:p3 [10 :h]] [:p4 [11 :d]]]))
