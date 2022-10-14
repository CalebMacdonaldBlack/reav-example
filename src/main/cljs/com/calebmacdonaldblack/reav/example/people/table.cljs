(ns com.calebmacdonaldblack.reav.example.people.table
  (:require [com.wsscode.pathom3.connect.operation :as pco]))

(pco/defresolver people-table-row-ui
  [{person-name :person/name :person/keys [id]}]
  {:ui/people-table-row
   ^{:key id}
   [:tr
    [:td id]
    [:td person-name]
    [:td [:button {} "Remove"]]]})

(pco/defresolver people-table-ui
  [{:keys [people] :ui/keys [add-person-button add-person-input]}]
  {::pco/input [{:people [:ui/people-table-row]} :ui/add-person-button :ui/add-person-input]}
  {:ui/people-table
   [:table
    [:thead
     [:tr
      [:th "ID"]
      [:th "Name"]
      [:th "Actions"]]]
    [:tbody
     (map :ui/people-table-row people)
     [:tr
      [:td]
      [:td add-person-input]
      [:td add-person-button]]]]})
