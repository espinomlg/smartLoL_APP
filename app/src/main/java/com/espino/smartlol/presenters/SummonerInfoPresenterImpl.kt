package com.espino.smartlol.presenters

import com.espino.smartlol.interfaces.SummonerInfoPresenter


class SummonerInfoPresenterImpl(var view: SummonerInfoPresenter.View): SummonerInfoPresenter{

    val urlEndpoint: String = "summoner/"
 //  val apiController: APIController = APIController(view.getContext())

    override fun getSummonerInfo(summonerName: String) {
      /*  apiController.get(urlEndpoint + summonerName) { response->
            view.makeTransition(response)
        }*/
    }

}