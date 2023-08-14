package com.pristonit.application.service.item;

import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.item.ItemDto;
import java.util.Optional;

public interface ItemQueryService {

	Optional<ItemDto> getOptionalItem(String itemId);

	EPage<ItemDto> getItemsList(FilterParameters parameters, PageRequest pageRequest);
}
