package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.commands.UnitOfMeasureCommand;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
