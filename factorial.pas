(do
    (read n)
    (= i 2)
    (= f 1)
    (while (<= i n)
        (do
            (= f (* f i))
            (= i (+ i 1))
        )
    )
    (print f)
)