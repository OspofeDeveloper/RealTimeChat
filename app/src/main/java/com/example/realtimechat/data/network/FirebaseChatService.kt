package com.example.realtimechat.data.network

import com.example.realtimechat.data.network.dto.MessageDto
import com.example.realtimechat.data.network.response.MessageResponse
import com.example.realtimechat.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMsgToFirebase(messageDto: MessageDto) {
        val newMsg = reference.child(PATH).push()
        newMsg.setValue(messageDto)
    }

    fun getMessage(): Flow<List<MessageModel>> {
        /** Le pedimos a reference que nos envie toda la info del path especificado */
        return reference.child(PATH).snapshots.map {dataSnapshot ->
            /** Le pedimos que por cada hijo nos devuelva los que no son null para evitar fallos */
            dataSnapshot.children.mapNotNull {
                /** Modelamos la respuesta a nuestro modelo MessageResponse. Lo podemos hacer
                 *  de esta forma porque el objeto que hay en Firebase (MessageDto) tiene
                 *  EXACTAMENTE el mismo formato que MessageResponse */
                it.getValue(MessageResponse::class.java)?.toDomain()
            }
        }
    }
}