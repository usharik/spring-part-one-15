package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.geekbrains.persist.Role;
import ru.geekbrains.persist.RoleRepository;

@Component
public class StringToRoleDtoConverter implements Converter<String, RoleDto> {

    private final RoleRepository roleRepository;

    @Autowired
    public StringToRoleDtoConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto convert(String s) {
        Role role = roleRepository.getOne(Long.parseLong(s));
        return new RoleDto(role.getId(), role.getName());
    }
}
