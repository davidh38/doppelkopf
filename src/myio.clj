(ns myio)

(defn play-card-inp []
  (eval (read-string (read-line))))

(defn myshuffle [cards]
  (shuffle cards)
  )
