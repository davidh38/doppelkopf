(ns test.test2.myfixture)


(defn myclosure []
  (fn [f]
    (println "closure -5-")
    (f)
    (println "closure -6-")
    )

  )
