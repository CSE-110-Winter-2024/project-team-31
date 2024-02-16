package edu.ucsd.cse110.successorator.app.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(GoalEntity flashcard);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<GoalEntity> flashcards);

    @Query("SELECT * FROM GoalEntity WHERE id = :id")
    GoalEntity find(int id);

    @Query("SELECT * FROM GoalEntity ORDER BY sort_order")
    List<GoalEntity> findAll();

    @Query("SELECT * FROM GoalEntity WHERE id = :id")
    LiveData<GoalEntity> findAsLiveData(int id);

    @Query("SELECT * FROM GoalEntity ORDER BY sort_order")
    LiveData<List<GoalEntity>> findAllAsLiveData();

    @Query("SELECT COUNT(*) FROM GoalEntity")
    int count();

    @Query("SELECT MIN(sort_order) FROM GoalEntity")
    int getMinSortOrder();

    @Query("SELECT MAX(sort_order) FROM GoalEntity")
    int getMaxSortOrder();

    @Query("UPDATE GoalEntity SET sort_order = sort_order + :by " +
        "WHERE sort_order >= :from AND sort_order <= :to")
    void shiftSortOrders(int from, int to, int by);

    @Transaction
    default int append(GoalEntity flashcard) {
        var maxSortOrder = getMaxSortOrder();
        var newFlashcard = new GoalEntity(
                flashcard.front, maxSortOrder + 1
        );
        return Math.toIntExact(insert(newFlashcard));
    }

    @Transaction
    default int prepend(GoalEntity flashcard) {
        shiftSortOrders(getMinSortOrder(), getMaxSortOrder(), 1);
        var newFlashcard = new GoalEntity(
                flashcard.front, getMinSortOrder() - 1
        );
        return Math.toIntExact(insert(newFlashcard));
    }

    @Query("DELETE FROM GoalEntity WHERE id = :id")
    void delete(int id);


}
