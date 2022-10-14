(ns com.calebmacdonaldblack.reav.example.core
  (:require
    [reagent.dom :as rdom]
    [com.calebmacdonaldblack.reav.example.view :as view]
    [datascript.core :as d]
    [reagent.core :as r]))

(def initial-data [{:person/id -2 :person/name "Caleb"}
                   {:person/id -1 :person/name "Erin"}
                   {:ui/id              :default
                    :ui.person.add/name ""}])

(def schema {:ui/id       {:db/unique :db.unique/identity}
             :person/id   {:db/unique      :db.unique/identity
                           :db/cardinality :db.cardinality/one}
             :person/name {:db/cardinality :db.cardinality/one}})

(defonce db (r/atom nil))
(defonce conn (r/atom nil))

(defn ^:dev/after-load mount-root []
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [view/ui {:d/db db :d/conn conn} :ui/app] root-el)))

(defn ^:export init [& args]
  (reset! conn (d/create-conn schema))
  (reset! db (d/db @conn))

  ;; Update db ratom when a transaction is applied to conn
  (d/listen! @conn (comp (partial reset! db) :db-after))

  ;; Add initial data
  (d/transact @conn initial-data)

  (mount-root))
