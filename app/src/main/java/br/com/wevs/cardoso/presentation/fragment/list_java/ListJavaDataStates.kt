package br.com.wevs.cardoso.presentation.fragment.list_java

import br.com.wevs.cardoso.domain.model.TopJava

sealed class ListJavaDataStates {
    data class CallSucess(val topJava: TopJava) : ListJavaDataStates()
    object CallError : ListJavaDataStates()
}

sealed class ListJavaInteractor {
    data class GetListTopJava(val page: Int) : ListJavaInteractor()
}