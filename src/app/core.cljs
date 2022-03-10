(ns app.core
  (:require [reagent.dom :as rdom]
            
            [app.data :as d]))

(defn- header-component
  []
  [:div.py-4
   [:div.max-w-7xl.mx-auto.px-4
    [:div.text-center
     [:p.mt-2.text-3xl.leading-8.font-extrabold.tracking-tight.text-gray-900 "Bob Ross' Colors"]]]])

(defn- color-swatch-component
  [color]
  (let [name (color :color/name)
        hex (color :color/hex)]
    [:li.col-span-1.flex.flex-col.text-center.bg-white.bounded-lg.shadow.p-10
     [:div.w-32.h-32.flex-shrink-0.mx-auto.border {:style { :background-color hex}}]
     [:p.text-gray-900.font-semibold.hover:text-gray-600.pt-5.uppercase name]
     [:p.text-gray-900.font-medium.hover:text-gray-600 hex]]))


(defn app
  []
  [:div
    [header-component]
    [:div.max-w-7xl.mx-auto.px-4.pt-4.pb-8
      [:ul.grid.grid-cols-2.gap-x-4.gap-y-8
        (for [color d/colors]
          ^{:key (color :color/hex)} (color-swatch-component color))]]])

(defn start []
  (rdom/render [app] (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
