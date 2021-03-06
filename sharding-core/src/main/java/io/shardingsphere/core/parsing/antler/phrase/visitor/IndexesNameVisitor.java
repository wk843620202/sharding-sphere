/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antler.phrase.visitor;

import io.shardingsphere.core.parsing.antler.util.ASTUtils;
import io.shardingsphere.core.parsing.antler.util.RuleNameConstants;
import io.shardingsphere.core.parsing.antler.util.VisitorUtils;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.ddl.DDLStatement;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Visit Multiple index name phrase.
 * 
 * @author duhongjun
 */
public final class IndexesNameVisitor implements PhraseVisitor {
    
    @Override
    public void visit(final ParserRuleContext ancestorNode, final SQLStatement statement) {
        DDLStatement ddlStatement = (DDLStatement) statement;
        String tableName = null;
        if (!ddlStatement.getTables().isEmpty()) {
            tableName = ddlStatement.getTables().getSingleTableName();
        }
        for (ParserRuleContext each : ASTUtils.getAllDescendantByRuleName(ancestorNode, RuleNameConstants.INDEX_NAME)) {
            statement.getSQLTokens().add(VisitorUtils.visitIndex(each, tableName));
        }
    }
}
