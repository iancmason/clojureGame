(ns game.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(defn move
  [entity direction]
  (case direction
    :down (assoc entity :y (dec (:y entity)))
    :up (assoc entity :y (inc (:y entity)))
    :right (assoc entity :x (inc (:x entity)))
    :left (assoc entity :x (dec (:x entity)))
    nil))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage) :camera (orthographic))
    (assoc (texture "cow.jpg")
           :x 50 :y 50 :width 100 :height 100
           :origin-x 0 :origin-y 0))
  
  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities))
  
  :on-key-down
  (fn [screen entities]
    (cond
      (= (:key screen) (key-code :dpad-up))
      (move (first entities) :up)
      (= (:key screen) (key-code :dpad-down))
      (move (first entities) :down)
      (= (:key screen) (key-code :dpad-right))
      (move (first entities) :right)
      (= (:key screen) (key-code :dpad-left))
      (move (first entities) :left)))
  
  :on-resize
  (fn [screen entities]
    (height! screen 600))
  
  )


(defgame game-game
  :on-create
  (fn [this]
    (set-screen! this main-screen)))

