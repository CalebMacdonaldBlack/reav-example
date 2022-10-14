(ns com.calebmacdonaldblack.reav.example.person.add
  (:require [com.wsscode.pathom3.connect.operation :as pco]
            [com.wsscode.pathom3.interface.eql :as p.eql]
            [datascript.core :as d]))


(pco/defmutation add-person
  [{:d/keys [conn]} {add-person-name :ui.person.add/name}]
  (d/transact! conn [{:person/id   (js/window.performance.now)
                      :person/name add-person-name}]))

(pco/defmutation clear-add-person-name
  [{:d/keys [conn]} _params]
  (d/transact! conn [[:db/add [:ui/id :default] :ui.person.add/name ""]]))

(pco/defmutation change-add-person-name
  [{:d/keys [conn]} {add-person-name :ui.person.add/name}]
  (d/transact! conn [{:ui/id :default :ui.person.add/name add-person-name}]))

(pco/defresolver can-submit?
  [{add-person-name :ui.person.add/name}]
  {:ui.person.add/can-submit?
   (not (empty? add-person-name))})

(pco/defresolver add-person-button-ui
  [env {:ui.person.add/keys [can-submit?]}]
  {:ui/add-person-button
   [:button {:disabled (not can-submit?)
             :on-click #(p.eql/process env [`(add-person) `(clear-add-person-name)])}
    "Create"]})

(pco/defresolver add-person-input-ui
  [env {add-person-name :ui.person.add/name}]
  {:ui/add-person-input
   [:input {:on-change #(p.eql/process-one env `(change-add-person-name {:ui.person.add/name ~(.. % -target -value)}))
            :value     add-person-name}]})
