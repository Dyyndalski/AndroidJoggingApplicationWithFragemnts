package com.example.testapp;

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_SPORTS)
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_SPORTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME + " TEXT,"
                + KEY_DETAILS + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)

        addDefaultData(db);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_SPORTS)
        Log.e("XD", "TA")
        onCreate(db)
    }

    fun getAllSports(): List<Sport> {
        val selectQuery = "SELECT * FROM " + TABLE_SPORTS
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        val allSport = arrayListOf<Sport>()

        if (cursor.moveToFirst()) {

            do {
                val sport = Sport()
                sport.id = cursor.getLong(0)
                sport.name = cursor.getString(1)
                sport.details = cursor.getString(2)
                allSport.add(sport);
            } while (cursor.moveToNext())
        }
        return allSport
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "bazaDanych"
        private const val TABLE_SPORTS = "sportTraces"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_DETAILS = "details"
        private var instance: DatabaseHandler? = null
        private var db: SQLiteDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): DatabaseHandler? {
            if (instance == null) {
                instance = DatabaseHandler(context)
                db = instance!!.writableDatabase
            }
            return instance
        }
    }

    private fun addDefaultData(db: SQLiteDatabase) {
        val s1 = Sport("Bobrowy Szlak", "Ścieżka edukacyjno-przyrodnicza “Bobrowy Szlak” rozpoczyna się we wsi Czmoniec (gmina Kórnik) i biegnie przez lasy, obszary łąk nadrzecznych z szypułkowymi dębami, skąd rozciąga się niepowtarzalny widok na występujące tam starorzecze Warty oraz jej naturalne rozlewisko. Trasa kończy swój bieg przy boisku w Czmońcu.");
        val s2 = Sport("Aktywna Trójka", "Z myślą o aktywnym wypoczynku na świeżym powietrzu przez cały rok, z inicjatywy Gmin: Mosina, Puszczykowo, Komorniki i Stęszew, przy współpracy z WPN powstał projekt pn. „Aktywna Trójka” – trasy trzech aktywności po Wielkopolskim Parku Narodowym. W ramach przeprowadzonych prac została wyznaczona sieć tras w formie zamkniętych pętli, przeznaczona do uprawiania nordic walking, biegania, a w zimie dla narciarstwa biegowego.");
        val s3 = Sport("Zbiorowiska roślinne wokół Jeziora Zielonka", "Ścieżka przyrodnicza wytyczona została wokół Jeziora Zielonka położonego w dużej enklawie śródleśnej na terenie Parku Krajobrazowego Puszcza Zielonka. Jej głównym celem jest zapoznanie uczestników z występującymi tu zbiorowiskami roślinnymi i przybliżenie zmian zachodzących w środowisku naturalnym, a także uświadomienie konieczności poszanowania otaczającej nas przyrody w obcowaniu na co dzień.");
        val s4 = Sport("Śnieżycowy Raj", "Rezerwat przyrody Śnieżycowy Jar. To magiczne miejsce przyciąga setki ludzi wczesną wiosną aby podziwiać gęsto rosnące w tym miejscu śnieżyce wiosenne. Kwiaty te tworzą piękne białe dywany ścielące głębię tych lasów. Osobą które nigdy tu nie były postaram się przybliżyć to miejsce. Na początku warto zaznaczyć, że w okresie kwitnięcia śnieżycy przy okazji dni wolnych teren ten jest użytkowany przez setki ludzi. Trasa oraz parkingi w okół rezerwatu są oblegane, dlatego warto zaplanować wypad tutaj w tygodniu lub w godzinach rannych aby uniknąć tłoku. Jednak dla osób, które wybierają się tutaj na niedzielny spacer trasa, którą zaproponowałem może być alternatywą,dzięki niej poza śnieżycą zobaczymy kilka innych mniej uczęszczanych miejsc.");
        val s5 = Sport("Rezerwat Meteoryt Morasko", "Wycieczka piesza do Rezerwatu Meteoryt Morasko.\n" +
                "Znajduje się on w północnej części Poznania, na Morasku i graniczy bezpośrednio z Suchym Lasem. Na jego terenie mieszczą się kratery, które zdaniem większości badaczy powstały w wyniku upadku meteorytu Morasko ok. 5 tys. lat temu. Polecam to miejsce.");
        val s6 = Sport("Ścieżka Dolina Samy", "Mostki przerzucone nad rzeką, piękna zielona ścieżka odchodząca od głównego szlaku, porośnięta paprociami i płożącymi roślinami, ciekawa konstrukcja/szałas z gałęzi. Dzieci podczas wycieczki obserwowały mrówki wspinające się jak po sznurku na drzewo, sprawdzały jak mocno kłują kolce akacji, biegały po ścieżce narysowanej patykiem, rzucały kamyki do wody, przyglądały się śladom pozostawionym przez sarenkę i… przesadzały rośliny na ścieżce");
        val s7 = Sport("Dolina Cybiny", "O ile sama ulica – Dolina Cybiny – zdaje się być dość często uczęszczana, o tyle dalszy odcinek (na mapie z prawej strony) zdaje się być znaczenie rzadziej odwiedzany (może by dość gęsto porośnięty roślinnością). Jeziorko ogląda się z pewnego oddalenia, jest nisko położone. Zamieszkuje je sporo ptaków.");
        val s8 = Sport("Radojewo", "Z pewnością pofalowana rzeźba starego parku, pozostałości po dawnej architekturze, pomniki przyrody, drewniana konstrukcja na wejściu, piękne duże liście łopianu. Gdy zdecydowaliśmy się na wyjście na otwartą przestrzeń, zaskoczył mnie złoty kolor pól, jego intensywność\uD83D\uDC9B");
        val s9 = Sport("Orkowo", "Mnóstwo zieleni – pola, wierzby, male jeziorka. Brzeg Warty obrośnięty dębami. Struga wpływająca do Warty i bardzo ciekawy przepust drogowy – #nieplaczabaw ;)");
        val s10 = Sport("Rezerwat Jezioro Drążynek ", "Ciekawa trasa wokół jeziora. Liczne ślady obecności bobrów. Położone jest niedaleko Kociałkowej Górki. Niedaleko znajduje Rezerwat Las Liścisty w Promnie");

        val toAdd = arrayListOf<Sport>(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10)

        toAdd.forEach {
            val values = ContentValues()
            values.put(KEY_NAME, it.name) // Contact Name
            values.put(KEY_DETAILS, it.details) // Contact Phone
            db.insert(TABLE_SPORTS, null, values)
        }
    }
}