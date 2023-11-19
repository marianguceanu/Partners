package com.example.partners

class PartnerRepo {
    private val partners = mutableListOf<Partner>()
    private var nextId = 1;

    fun getAll(): List<Partner> = partners

    fun getById(id: Int): Partner? = partners.find { it.id == id }

    fun add(partner: Partner) {
        partner.id = nextId++
        partners.add(partner)
    }

    fun update(updatedPartner: Partner) {
        partners.replaceAll { if (it.id == updatedPartner.id) updatedPartner else it }
    }

    fun delete(id: Int) {
        partners.removeIf { it.id == id }
    }

    fun clear() {
        partners.clear()
    }

    fun insertAll(partners: List<Partner>) {
        this.partners.addAll(partners)
    }
}