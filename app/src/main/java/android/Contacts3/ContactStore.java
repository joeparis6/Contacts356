package android.Contacts3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class ContactStore {

    //Singleton that holds all of the contacts and groups so that we don't have to use a database

    private static ContactStore store;

    private List<Contact> allContacts;
    private List<ContactGroup> allGroups;
    private List<String> allPlans;
    private Contact selectedContact;
    private ContactGroup selectedGroup;

    public static ContactStore getInstance() {
        if (store == null) {
            store = new ContactStore();
        }

        return store;
    }


    public ContactStore() {
        this.allPlans = new ArrayList<String>();
        this.allContacts = new ArrayList<Contact>();
        this.allGroups = new ArrayList<ContactGroup>() {
        };



        Contact contact1 = new Contact("Mom");
        Contact contact2 = new Contact("Dad");
        Contact contact3 = new Contact("Steve", "Jones");
        Contact contact4 = new Contact("Harrison", "James", "801 489 5252");
        Contact contact5 = new Contact("Jeffrey", "Thompson");
        Contact contact6 = new Contact("Sarah", "James");
        Contact contact7 = new Contact("Olivia", "Smith");
        Contact contact8 = new Contact("Grandma");
        Contact contact9 = new Contact("Betty", "Gonzalez");
        Contact contact10 = new Contact("Michael");
        Contact contact11 = new Contact("Mrs. Gilbert");
        Contact contact12 = new Contact("Cristian", "Pintos");
        Contact contact13= new Contact("Tom", "Brady");
        Contact contact14 = new Contact("Dr. Chang");
        Contact contact15= new Contact("Morgan", "Busch");
        Contact contact16= new Contact("Taeho", "Kim");
        Contact contact17 = new Contact("Stephanie");
        Contact contact18 = new Contact("Nicole");
        Contact contact19 = new Contact("Pete", "Peterson");
        Contact contact20 = new Contact("Karen Nguyen");


        allContacts.add(contact1);
        allContacts.add(contact2);
        allContacts.add(contact3);
        allContacts.add(contact4);
        allContacts.add(contact5);
        allContacts.add(contact6);
        allContacts.add(contact7);
        allContacts.add(contact8);
        allContacts.add(contact9);
        allContacts.add(contact10);
        allContacts.add(contact11);
        allContacts.add(contact12);
        allContacts.add(contact13);
        allContacts.add(contact14);
        allContacts.add(contact15);
        allContacts.add(contact16);
        allContacts.add(contact17);
        allContacts.add(contact18);
        allContacts.add(contact19);
        allContacts.add(contact20);

        ContactGroup group1 = new ContactGroup("Family");
        ContactGroup group2 = new ContactGroup("Work");
        ContactGroup group3 = new ContactGroup("Church");
        ContactGroup group4 = new ContactGroup("CS Project");
        ContactGroup group5 = new ContactGroup("Soccer Team");
        ContactGroup group6 = new ContactGroup("Volunteering");
        ContactGroup group7 = new ContactGroup("Business Connections");
        ContactGroup group8 = new ContactGroup("Mechanics");
        ContactGroup group9 = new ContactGroup("High School Friends");

        allGroups.add(group1);
        allGroups.add(group2);
        allGroups.add(group3);
        allGroups.add(group4);
        allGroups.add(group5);
        allGroups.add(group6);
        allGroups.add(group7);
        allGroups.add(group8);
        allGroups.add(group9);

        allPlans.add("Call the bishop");
        allPlans.add("Schedule a business lunch with Diane");
        allPlans.add("Organize family barbecue");

        group1.addContact(contact4);
        group1.addContact(contact6);
        group1.addContact(contact5);
        this.selectedGroup = group1;
        this.selectedContact = contact4;
    }

    public ContactGroup getGroup1() {
        return allGroups.get(0);
    }

    public ArrayList<Contact> getAllContacts() {
        return (ArrayList<Contact>) allContacts;
    }

    public ArrayList<ContactGroup> getAllGroups() {

        return (ArrayList<ContactGroup>) allGroups;
    }

    public void addContact(Contact contact) {
        allContacts.add(contact);
    }

    public void addGroup(ContactGroup group) {
        allGroups.add(group);
    }

    public void setSelectedContact(Contact contact) {
        this.selectedContact = contact;
    }

    public void setSelectedGroup(ContactGroup group) {
        this.selectedGroup = group;
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public ContactGroup getSelectedGroup() {
        return selectedGroup;
    }

    public void addPlan(String plan) {
        allPlans.add(plan);
    }

    public ArrayList<String> getAllPlans() {
        return (ArrayList<String>) allPlans;
    }


}
