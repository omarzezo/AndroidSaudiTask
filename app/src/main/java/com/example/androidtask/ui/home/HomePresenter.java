package com.example.androidtask.ui.home;

import com.example.androidtask.data.DataManager;
import com.example.androidtask.data.model.UsersPojoResponse;
import com.example.androidtask.ui.base.BasePresenter;
import com.example.androidtask.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomeMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public HomePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getHomeData(Integer offset ,Integer limit) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        getMvpView().showLoader();
        mDataManager.getHomeData(offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UsersPojoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable=d;
                    }

                    @Override
                    public void onNext(UsersPojoResponse usersPojoResponse) {

                        if (usersPojoResponse != null && usersPojoResponse.isStatus()&& usersPojoResponse.getData()!=null){
                            getMvpView().returnHomeData(usersPojoResponse);
                            if (!usersPojoResponse.getData().isHas_more()){
                                getMvpView().showEmpty();
                            }
                        } else {
                            getMvpView().showEmpty();
                        }
                        getMvpView().hideLoader();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
