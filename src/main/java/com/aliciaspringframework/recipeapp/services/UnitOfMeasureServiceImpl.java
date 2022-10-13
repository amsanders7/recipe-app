package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.commands.UnitOfMeasureCommand;
import com.aliciaspringframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.aliciaspringframework.recipeapp.repositories.UnitOfMeasureRepository;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
            .spliterator(), false)
            .map(unitOfMeasureToUnitOfMeasureCommand::convert)
            .collect(Collectors.toSet());
    }
}
