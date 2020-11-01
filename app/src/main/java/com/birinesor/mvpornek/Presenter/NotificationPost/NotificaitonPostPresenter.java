package com.birinesor.mvpornek.Presenter.NotificationPost;

public
interface NotificaitonPostPresenter {
    void postNotification(int bildirimYapanKullaniciID,int bildirimYapılanKullaniciID,int bildirimYapılanCevapID,int bildirimYapılanSoruID,String ileti,String durum,int bildirimTuru );
}
